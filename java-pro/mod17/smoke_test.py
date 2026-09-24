"""Executa três JARs locais já compilados e testa o fluxo HTTP com dados fictícios."""
from pathlib import Path
import subprocess,os,secrets,json,time,urllib.request,urllib.error,tempfile
root=Path(__file__).resolve().parent
processes=[];results=[]
def request(port,path,method='GET',data=None):
 req=urllib.request.Request(f'http://127.0.0.1:{port}{path}',data=json.dumps(data).encode() if data is not None else None,headers={'Content-Type':'application/json'},method=method)
 try:
  with urllib.request.urlopen(req,timeout=7) as r:return r.status,json.load(r)
 except urllib.error.HTTPError as e:return e.code,None
try:
 with tempfile.TemporaryDirectory(prefix='ebac-memelandia-') as tmp:
  for name,port in [('usuarios',18101),('categorias',18102),('memes',18103)]:
   log=open(Path(tmp)/(name+'.log'),'w')
   env=os.environ|{'SERVER_PORT':str(port),'DB_PASSWORD':secrets.token_urlsafe(32),'DB_URL':'jdbc:h2:mem:smoke_'+name,'USUARIOS_URL':'http://127.0.0.1:18101','CATEGORIAS_URL':'http://127.0.0.1:18102'}
   artifact='java-pro-mod17-'+name+'-service-1.0.0.jar'
   proc=subprocess.Popen(['java','-Dfile.encoding=UTF-8','-jar',str(root/(name+'-service')/'target'/artifact)],env=env,cwd=tmp,stdout=log,stderr=log);processes.append(proc)
   for attempt in range(60):
    if proc.poll() is not None:raise RuntimeError(name+' não iniciou')
    try:
     if request(port,'/actuator/health')[1]['status']=='UP':break
    except Exception:pass
    time.sleep(.5)
   else:raise RuntimeError(name+' não ficou pronto')
  status,user=request(18101,'/memelandia/usuarios','POST',{'nome':'Ana Exemplo','email':'ana@example.invalid'});assert status==201
  status,cat=request(18102,'/memelandia/categorias','POST',{'nome':'Programação','descricao':'Humor de exemplo','usuarioId':user['id']});assert status==201
  data={'nome':'Meme de exemplo','descricao':'Demonstração fictícia','url':'https://example.com/meme.png','usuarioId':user['id'],'categoriaId':cat['id']}
  status,meme=request(18103,'/memelandia/memes','POST',data);assert status==201
  assert request(18103,'/memelandia/memes/aleatorio')[1]['id']==meme['id']
  results.append('Fluxo real: usuário 201 -> categoria 201 -> meme 201 -> aleatório 200.')
  assert request(18103,'/memelandia/memes','POST',data|{'usuarioId':99999})[0]==422
  assert request(18103,'/memelandia/memes','POST',data|{'categoriaId':99999})[0]==422
  assert len(request(18103,'/memelandia/memes')[1])==1
  results.append('Usuário e categoria inexistentes: 422, sem gravar memes adicionais.')
  for port in [18101,18102,18103]:
   assert 'http.server.requests' in request(port,'/actuator/metrics')[1]['names']
   assert request(port,'/actuator/metrics/http.server.requests')[0]==200
  results.append('Métricas HTTP disponíveis nos três processos.')
  processes[0].terminate();processes[0].wait(timeout=10)
  assert request(18103,'/memelandia/memes','POST',data)[0]==503
  assert len(request(18103,'/memelandia/memes')[1])==1
  results.append('Usuários parado: criação retorna 503 e preserva o único meme válido.')
  for proc in processes:
   if proc.poll() is None:proc.terminate()
  for proc in processes:proc.wait(timeout=10)
  for name in ['usuarios','categorias','memes']:
   logs=(Path(tmp)/(name+'.log')).read_text()
   assert 'metodo=' in logs and 'duracaoMs=' in logs
   assert 'ana@example.invalid' not in logs
  results.append('Logs de acesso presentes, sem o e-mail do exemplo.')
finally:
 for proc in processes:
  if proc.poll() is None:
   proc.terminate()
   try:proc.wait(timeout=10)
   except subprocess.TimeoutExpired:proc.kill()
(root/'verificacao-http.txt').write_text('\n'.join(results)+'\n')
print('\n'.join(results))
