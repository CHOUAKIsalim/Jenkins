pipeline {
  agent any
  stages {
    stage('Build') {
      post {
        failure {
          mail(subject: 'Jinkens Test Failed', body: 'Mail Notification of the  Integration JAVA API with Jenkins', bcc: 'fs_chouaki@esi.dz ', cc: 'fs_chouaki@esi.dz')

        }

      }
      steps {
        sh 'gradle build'
        sh 'gradle javadoc'
        sh 'gradle jar'
        archiveArtifacts 'build/libs/*.jar'
        archiveArtifacts 'build/docs/javadoc/'
      }
    }
    stage('Code Analysis') {
      parallel {
        stage('Code Analysis') {
          steps {
            withSonarQubeEnv('sonarqube') {
              bat 'C:\\sonar-scanner-3.2.0.1227-windows\\bin\\sonar-scanner'
            }
            waitForQualityGate false
        
          }
        }
        stage('Test reporting') {
          steps {
            jacoco(minimumInstructionCoverage: '60', minimumLineCoverage: '60')
          }
        }
      }
    }
    stage('Deployment') {
      when {
        not {
          changeRequest target: 'master'
        }

      }
      steps {
        sh 'gradle uploadArchives'
      }
    }
    stage('Slack Notification') {
      when {
        not {
          changeRequest target: 'master'
        }

      }
      steps {
        slackSend(message: 'Testing Jenkins')
      }
    }
  }
}
