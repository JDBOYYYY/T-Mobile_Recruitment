pipeline {
    agent any

    tools {
        jdk 'jdk-21' // Ensure this matches the name configured in Jenkins
        maven 'Maven' // Ensure this matches the name configured in Jenkins
    }

    environment {
        JAVA_HOME = tool name: 'jdk-21', type: 'jdk'
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Verify Tools') {
            steps {
                script {
                    // Check tool versions (minimal logging)
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
                    // Clean and install the project
                    sh 'mvn clean install'

                    // Run tests
                    sh 'mvn test'
                }
            }
        }
    }

    post {
        always {
            // Archive the test results and generate a report
            junit '**/target/surefire-reports/*.xml'

            // Archive any other relevant artifacts (e.g., logs, screenshots)
            archiveArtifacts artifacts: '**/target/**', allowEmptyArchive: true
        }
    }
}
