pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'gradle build'
        sh 'gradle javadoc'
        sh 'gradle jar'
        archiveArtifacts 'build/libs/*.jar'
        archiveArtifacts 'build/docs/javadoc/'
      }
        post {
          failure {
            mail(subject: 'Jinkens Test Failed', body: 'Mail Notification of the  Integration JAVA API with Jenkins', bcc: 'fm_bouteminel@esi.dz ', cc: 'fm_boutemine@esi.dz')
                  }
            }
      }
      stage('Mail Notification') {
      steps {
         mail(subject: 'Jinkens Test Successful', body: 'Mail Notification of the  Integration JAVA API with Jenkins', bcc: 'fs_chouaki@esi.dz ', cc: 'fs_chouaki@esi.dz')
            }
      }
      stage('Code Analysis') {
        parallel {
          stage('Code Analysis') {
            steps {
              withSonarQubeEnv('sonarqube') {
                sh 'C:\sonarqube-7.3'
            }

            waitForQualityGate true
          }
        }
        stage('Test reporting') {
          steps {
            jacoco(minimumInstructionCoverage: '60', minimumLineCoverage: '60')
          }
        }
      }
    }
    
