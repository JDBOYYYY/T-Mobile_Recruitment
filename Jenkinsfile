pipeline {
    agent any

    tools {
        jdk 'jdk-21'
        maven 'Maven'
    }

    environment {
        JAVA_HOME = tool name: 'jdk-21', type: 'jdk'
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Verify Tools') {
            steps {
                script {
                    sh 'java -version'
                    sh 'mvn -version'
                    sh 'google-chrome --version'
                    sh 'chromedriver --version'
                }
            }
        }
        stage('Build and Test') {
            steps {
                script {
                    sh 'mvn clean install'
                }
            }
        }
    }

    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/**', allowEmptyArchive: true
        }
    }
}
