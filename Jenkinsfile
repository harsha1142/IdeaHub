pipeline {
    agent any

    stages {
        stage('Debug Workspace') {
            steps {
                sh '''
                  docker run --rm \
                    -v "$PWD":/workspace \
                    maven:3.9.9-eclipse-temurin-17 \
                    sh -c "
                      echo '--- workspace ---';
                      ls -la /workspace;
                      echo '--- backend ---';
                      ls -la /workspace/backend || true;
                      echo '--- backend/demo ---';
                      ls -la /workspace/backend/demo || true;
                    "
                '''
            }
        }
    }
}
