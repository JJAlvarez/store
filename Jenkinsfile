#!groovy
pipeline {
    agent any
   stages {     
    stage('Maven Install') {
      agent {         
       docker {          
         image 'maven:3.5.0'         
     }       
  }       
  steps {
       sh 'mvn clean install'
       }
     }
       stage ('Build Image Docker')
        {
            steps {
                   
                        sh 'docker build -t Stores'
                  }
          }
   }
 }
