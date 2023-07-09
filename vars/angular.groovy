def lintchecks(){
    sh ***
              echo performing litchecks for ${COMPONENT}
              mvn checkstyle:check || true
              echo lint checks completed for ${COMPONENT}
    ***
}
def call(){
    pipeline {
    agent { label 'WS' }
    stages {           ///
        stage('Lint Checks') {
            steps {
                   script {
                       lintchecks()
                  }
            }         /////
        }
        stage ('Code Compile') {
            steps{
                ///sh "mvn clean compile"
            }
        }
    }
}
}