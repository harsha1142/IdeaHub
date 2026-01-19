pipeline {
    agent any

    tools {
        jdk 'jdk17'
        maven 'maven3'
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/harsha1142/IdeaHub.git'
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend/demo') {
                    bat 'mvn clean install -DskipTests'
                }
            }
        }
    }
}
