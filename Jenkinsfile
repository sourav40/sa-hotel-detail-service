pipeline {

   agent any

      stages {

         stage('Build') {

             steps {
               sh 'echo Build and compiling.'
               sh 'pwd'
               sh './mvnw clean install -Dmaven.test.skip=true'
             }
}

          stage('Build Docker Image') {
              steps {
                      script{
                        sh 'docker build -t sourav40/hotel-detail-service .'
                      }
     }
     }

            stage('Push Docker Image To Docker Hub') {
                   steps {
                           script{
//                            sh 'docker tag hotel-detail-service:latest sourav40/hotel-detail-service:latest'
                           sh 'docker login -u sourav40 -p dckr_pat_9_M2GkzkEBiGSyUJf4JVjXt_PPE'
                           sh 'docker push sourav40/hotel-detail-service:latest'
                }
          }
          }
}
}