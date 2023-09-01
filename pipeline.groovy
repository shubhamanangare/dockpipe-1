pipeline {
    agent any 
    stages {
        stage('Pull') { 
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/shubhamanangare/dockpipe-1.git']])
            }
        }
       
        stage('Build Docker Image') {
            sh docker build -t tomcat .
            sh docker run -d -p 8082:8080 tomcat
        }
    }
}
