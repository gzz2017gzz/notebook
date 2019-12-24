日志的使用
1  指定包下的日志级别
log4j.logger. 固定写法
org.springframework 包名
0=error 日志级别  debug info trace off 
2 使用日志输信息好处 输出时间 具体类 具体行 

3 生产环境中可以修改日志级别 减少日志输出量 (提高性能)

4.
		logger.info("来吧"); //重要提示信息
		logger.debug("来吧");//纯调试用的信息
		logger.error("来吧");//错误异常进用这种