pipeline {
    agent any

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-11-openjdk'  // Set your Java path
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning repository...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building the Java project...'
                sh 'javac src/*.java -d out'  // Compile all Java files into the `out` directory
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh '''
                if [ -d "out" ]; then
                    java -cp out Main
                else
                    echo "No compiled classes found!"
                    exit 1
                fi
                '''
            }
        }

        stage('Archive Artifacts') {
            steps {
                echo 'Archiving build artifacts...'
                archiveArtifacts artifacts: 'out/*.class', fingerprint: true
            }
        }

        stage('Clean Up') {
            steps {
                echo 'Cleaning workspace...'
                cleanWs()
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
