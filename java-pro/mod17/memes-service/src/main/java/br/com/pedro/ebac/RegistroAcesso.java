package br.com.pedro.ebac;

import jakarta.servlet.*;import jakarta.servlet.http.*;import org.springframework.stereotype.Component;import org.springframework.web.filter.OncePerRequestFilter;import org.springframework.web.servlet.HandlerMapping;import org.slf4j.*;import java.io.IOException;
 @Component public class RegistroAcesso extends OncePerRequestFilter {
 private static final Logger log=LoggerFactory.getLogger(RegistroAcesso.class);
 @Override protected void doFilterInternal(HttpServletRequest req,HttpServletResponse res,FilterChain chain) throws ServletException,IOException {
 long inicio=System.nanoTime();try{chain.doFilter(req,res);}finally{Object rota=req.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);log.info("metodo={} rota={} status={} duracaoMs={}",req.getMethod(),rota==null?"sem_rota":rota,res.getStatus(),(System.nanoTime()-inicio)/1_000_000);}
 }
 }
