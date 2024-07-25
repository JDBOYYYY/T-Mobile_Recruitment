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
                    // Print JAVA_HOME to verify it's correctly set
                    sh 'echo JAVA_HOME=$JAVA_HOME'

                    // Print Java version to verify the correct JDK is being used
                    sh 'java -version'

                    // Print Java compiler version to verify the correct JDK is being used
                    sh 'javac -version'

                    // Print Maven version to verify Maven is correctly set
                    sh 'mvn -version'
                }
            }
        }
        stage('Build') {
            steps {
                script {
                    // Running Maven build command
                    sh 'mvn clean install'
                }
            }
        }
    }
}
