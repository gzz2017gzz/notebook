/*  1:   */ package com.dl.notebook.common.controller;
/*  2:   */ 
/*  3:   */ import org.apache.commons.logging.Log;
/*  4:   */ import org.apache.commons.logging.LogFactory;
/*  5:   */ import org.springframework.stereotype.Controller;
/*  6:   */ import org.springframework.web.bind.annotation.RequestMapping;
/*  7:   */ import org.springframework.web.bind.annotation.RequestParam;
/*  8:   */ 
/*  9:   */ @Controller
/* 10:   */ public class LoginController
/* 11:   */ {
/* 12:11 */   private Log logger = LogFactory.getLog(getClass());
/* 13:   */   
/* 14:   */   @RequestMapping({"/login"})
/* 15:   */   public String login(@RequestParam(name="error", required=false) String error)
/* 16:   */   {
/* 17:15 */     if (error != null) {
/* 18:16 */       this.logger.info(error);
/* 19:   */     }
/* 20:17 */     return "login";
/* 21:   */   }
/* 22:   */   
/* 23:   */   @RequestMapping({"/"})
/* 24:   */   public String index()
/* 25:   */   {
/* 26:22 */     return "index";
/* 27:   */   }
/* 28:   */   
/* 29:   */   @RequestMapping({"/logout"})
/* 30:   */   public void logout() {}
/* 31:   */ }


/* Location:           D:\bak\notebook.jar
 * Qualified Name:     BOOT-INF.classes.com.dl.notebook.common.controller.LoginController
 * JD-Core Version:    0.7.0.1
 */