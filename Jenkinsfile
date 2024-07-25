pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/JDBOYYYY/T-Mobile_Recruitment.git'
            }
        }
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
