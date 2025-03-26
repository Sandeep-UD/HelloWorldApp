pipeline {
    agent any

    environment {
        JAVA_HOME = '/usr/lib/jvm/java-11-openjdk'  // Set your Java path
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
        GITHUB_REPO = 'your-org/your-repo'  // Replace with your GitHub org/repo
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning repository...'
                checkout scm

                // GitHub status check: Pending
                githubNotify context: 'Checkout', status: 'PENDING', description: 'Checking out repository'
            }
            post {
                success {
                    githubNotify context: 'Checkout', status: 'SUCCESS', description: 'Checkout completed'
                }
                failure {
                    githubNotify context: 'Checkout', status: 'FAILURE', description: 'Checkout failed'
                }
            }
        }

        stage('Build') {
            steps {
                echo 'Building the Java project...'

                // GitHub status check: Pending
                githubNotify context: 'Build', status: 'PENDING', description: 'Building project'

                // Cross-platform build commands
                script {
                    if (isUnix()) {
                        sh 'javac src/*.java -d out'  // Linux/Unix: Compile Java files
                    } else {
                        bat 'mvn clean install'       // Windows: Maven build
                    }
                }
            }
            post {
                success {
                    githubNotify context: 'Build', status: 'SUCCESS', description: 'Build succeeded'
                }
                failure {
                    githubNotify context: 'Build', status: 'FAILURE', description: 'Build failed'
                }
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'

                // GitHub status check: Pending
                githubNotify context: 'Test', status: 'PENDING', description: 'Running tests'

                script {
                    if (isUnix()) {
                        sh '''
                        if [ -d "out" ]; then
                            java -cp out Main
                        else
                            echo "No compiled classes found!"
                            exit 1
                        fi
                        '''
                    } else {
                        bat 'mvn test'   // Windows test execution
                    }
                }
            }
            post {
                success {
                    githubNotify context: 'Test', status: 'SUCCESS', description: 'Tests passed'
                }
                failure {
                    githubNotify context: 'Test', status: 'FAILURE', description: 'Tests failed'
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo 'Build succeeded!'
        }
        failure {
            echo 'Build failed!'
        }
    }
}
