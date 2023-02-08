# numberfiddler
Backend to an angular app that recives files uploaded from front end which it processes, save to upload folder path to which is defined in FileResource.
The data extracted from files is stored in Mysql data base which you may have to create, the expected defenitions for it are located in ./resourced/application.properties.
to create the requared data base use Mysql command line and "create database numberfiddler;"
the spring.jpa.hibernate.ddl-auto is set to "create" if you want to keep old data in the data base use "update" instead