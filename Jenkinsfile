pipeline {
    agent any

    environment {
        MAVEN_HOME = "${WORKSPACE}/maven"
        PATH = "${MAVEN_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Install Maven') {
            steps {
                script {
                    def mavenVersion = '3.8.4'
                    def mavenUrl = "https://downloads.apache.org/maven/maven-3/${mavenVersion}/binaries/apache-maven-${mavenVersion}-bin.tar.gz"
                    def mavenHome = "${env.MAVEN_HOME}"

                    sh '''
                       # Define the Maven version and download URL
                       MAVEN_VERSION=3.8.4
                       MAVEN_URL=https://downloads.apache.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz

                       # Create a directory for Maven installation
                       mkdir -p $MAVEN_HOME

                       # Download and extract Maven
                       curl -o maven.tar.gz $MAVEN_URL

                       if file maven.tar.gz | grep -q 'gzip compressed data'; then
                           tar -xzf maven.tar.gz -C $MAVEN_HOME --strip-components=1
                           echo "Maven downloaded and extracted successfully"
                       else
                           echo "Downloaded file is not a valid gzip file"
                           cat maven.tar.gz
                           exit 1
                       fi

                       # Clean up
                       rm maven.tar.gz
                    '''
                }
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
