pipeline {
    agent any

    tools {
        maven 'Maven 3.x'
    }

    stages {
        stage('Build and Test') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
        }
    }
}
