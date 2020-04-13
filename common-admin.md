##  后台管理系统

### 1. 功能说明

<details id="repo-meta-edit" class="Details-element details-reset js-dropdown-details " style="box-sizing: border-box; display: block; color: rgb(36, 41, 46); font-family: -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Helvetica, Arial, sans-serif, &quot;Apple Color Emoji&quot;, &quot;Segoe UI Emoji&quot;; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-style: initial; text-decoration-color: initial;"><summary class="d-block" style="box-sizing: border-box; display: block !important; cursor: pointer; list-style: none;"><div class="Details-content--closed f4" style="box-sizing: border-box; font-size: 16px !important;"><div class="d-flex flex-items-start" style="box-sizing: border-box; align-items: flex-start !important; display: flex !important;"><span class="flex-auto mb-2" style="box-sizing: border-box; flex: 1 1 auto !important; margin-bottom: 8px !important;"><div class="f4" style="box-sizing: border-box; font-size: 16px !important;"><span class="text-gray-dark mr-2" itemprop="about" style="box-sizing: border-box; color: rgb(36, 41, 46) !important; margin-right: 8px !important;">后台管理（Springboot+shiro+freemarker+mysql）。拥有基础的菜单管理、用户管理、角色管理等，菜单管理动态生成菜单、权限内容，开发者可以直接拿来使用。项目结构清晰、通俗易懂，是做一个后台管理系统的最佳选择，同时也可以作为任何系统的基础脚手架。</span></div></span><span class="btn btn-sm" style="box-sizing: border-box; position: relative; display: inline-block; padding: 3px 10px; font-size: 12px; font-weight: 600; line-height: 20px; white-space: nowrap; vertical-align: middle; cursor: pointer; user-select: none; background-repeat: repeat-x; background-position: -1px -1px; background-size: 110% 110%; border: 1px solid rgba(27, 31, 35, 0.2); border-radius: 0.25em; -webkit-appearance: none; color: rgb(36, 41, 46); background-color: rgb(239, 243, 246); background-image: linear-gradient(-180deg, rgb(250, 251, 252), rgb(239, 243, 246) 90%);">Edit</span></div></div></summary></details>



https://github.com/tulip36/common-admin



### 2. 将该项目下载下来后，需要改动如下部分才能运行。

- 在pom.xml里，将下面的版本号该为1.5.22。

  ```xml
  	<parent>
  		<groupId>org.springframework.boot</groupId>
  		<artifactId>spring-boot-starter-parent</artifactId>
  		<version>1.5.6.RELEASE</version>
  		<relativePath/> <!-- lookup parent from repository -->
  	</parent>
  
  ```

- 在src/main/resources/config/logback.xml里，将/export前面的/去掉。

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <configuration
          scan="true" scanPeriod="10 seconds">
      <include resource="org/springframework/boot/logging/logback/base.xml"/>
      <appender name="INFO_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
          <File>export/Logs/common-admin/info.log</File>
          <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
              <fileNamePattern>export/Logs/common-admin/info-%d{yyyyMMdd}.log.%i</fileNamePattern>
              <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                  <maxFileSize>500MB</maxFileSize>
              </timeBasedFileNamingAndTriggeringPolicy>
              <maxHistory>2</maxHistory>
          </rollingPolicy>
          <layout class="ch.qos.logback.classic.PatternLayout">
              <Pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} -%msg%n
              </Pattern>
          </layout>
      </appender>
  
      <appender name="ERROR_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
          <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
              <level>ERROR</level>
          </filter>
          <File>export/Logs/common-admin/error.log</File>
          <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
              <fileNamePattern>/export/Logs/common-admin/error-%d{yyyyMMdd}.log.%i
              </fileNamePattern>
              <timeBasedFileNamingAndTriggeringPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP">
                  <maxFileSize>500MB</maxFileSize>
              </timeBasedFileNamingAndTriggeringPolicy>
              <maxHistory>2</maxHistory>
          </rollingPolicy>
          <layout class="ch.qos.logback.classic.PatternLayout">
              <Pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} -%msg%n
              </Pattern>
          </layout>
      </appender>
      <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
          <encoder>
              <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
              </pattern>
          </encoder>
      </appender>
      <!-- 这一句至关重要如果没有，就无法输出sql语句 -->
      <logger name="com.common.system.mapper" level="DEBUG"/>
      <root level="INFO">
          <appender-ref ref="INFO_FILE"/>
          <appender-ref ref="ERROR_FILE"/>
      </root>
  </configuration>
  ```

  ### 3. 登录密码是super, 123456

  