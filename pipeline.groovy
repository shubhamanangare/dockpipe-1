pipeline {
    agent any 
    stages {
        stage('Pull') { 
            steps {
                script {
                    checkout([$class: 'GitSCM',
                        branches: [[name: '*/main']],
                        userRemoteConfigs: [[url: 'https://github.com/shubhamanangare/dockpipe-1.git']]])
                }
            }
        }
       
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t tomcat .'
                sh 'docker run -d -p 8082:8080 tomcat'
            }
        }
    }
}
