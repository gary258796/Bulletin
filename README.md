Project: Bulletin Practice

    Practice using Spring Boot + Vue.js 

Notice:

    application.properties isn't included in this repository
    
    Add application.properties under src/main/resources with content:
    
        # IP
        api.url = http://localhost:8080
        
        spring.thymeleaf.cache = true
        
        ################### JavaMail Configuration ##########################
        support.email = ##Replace with your gmail account### 
        spring.mail.host=smtp.gmail.com
        spring.mail.port=465
        spring.mail.protocol=smtps
        spring.mail.username= ##Replace with your gmail account###
        spring.mail.password= ##Replace with your gmail password###
        spring.mail.properties.mail.transport.protocol=smtps
        spring.mail.properties.mail.smtps.auth=true
        spring.mail.properties.mail.smtps.starttls.enable=true
        spring.mail.properties.mail.smtps.timeout=8000
        
        ################### H2 Console Configuration ##########################
        # h2 DB path and name 
                spring.datasource.url=jdbc:h2:mem:bulletindb 
        spring.h2.console.enabled=true
    