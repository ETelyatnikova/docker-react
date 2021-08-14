def gv
pipeline {
 agent none
 parameters {
  choice(name:'VERSION', choices:['1.1.0', '1.2.0', '1.3.0'], description: '')
  booleanParam(name:'executeTests', defaultValue: true, description:'')
 }
  stages {
    stage("init") {
      agent any
      steps {
       script {
        gv = load 'script.groovy'
       }
      }
    }

   stage("buildApp") {
     agent {
       docker {
        image 'node:lts-buster-slim'
        args '-p 3000:3000'
        reuseNode true
       }
      }

      steps {
       script {
        gv.buildApp()
       }
      }
    }

   stage("buildImage") {
     agent {
       docker {
        image 'node:lts-buster-slim'
        args '-p 3000:3000'
        reuseNode true
       }
      }

     steps {
       script {
        gv.buildImage()
       }
      }
    }
    
    stage("test") {
      agent any
     when {
      expression {
       params.executeTests
      }
     }
      steps {
       script {
        gv.testApp()
       }
      }
    }
    
    stage("deploy") {
      agent any
      steps {
       script {
        gv.deployApp()
       }
      }
    }
  }
}
