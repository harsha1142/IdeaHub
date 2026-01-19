pipeline {
    agent any

    tools {
        jdk 'jdk17'
        maven 'maven3'
        nodejs 'node18'
    }

    environment {
        MAVEN_OPTS = '-Xmx1024m'
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo 'Checking out source code'
                git branch: 'main',
                    url: 'https://github.com/harsha1142/IdeaHub.git'
            }
        }

        stage('Build Backend') {
            steps {
                echo 'Building Spring Boot backend'
                dir('backend/demo') {
                    bat 'mvn clean install -DskipTests'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                echo 'Building frontend'
                dir('frontend') {
                    bat 'npm install'
                    bat 'npm run build'
                }
            }
        }
    }

    post {
        success {
            echo 'CI Pipeline completed successfully'
        }

        failure {
            echo 'CI Pipeline failed'
        }

        always {
            echo 'Pipeline execution finished'
        }
    }
}
