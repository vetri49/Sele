pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Checkout the source code
                    checkout scm
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    // Use the Maven tool installed in Jenkins
                    def mvnHome = tool 'Maven'
                    sh "${mvnHome}/bin/mvn clean install"
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Add any additional test steps here
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    
                }
            }
        }
    }

    post {
        success {
            
        }
        failure {
           
        }
    }
}
