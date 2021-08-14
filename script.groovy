def buildApp() {
  echo 'building the app...'
  sh 'npm install'
  sh 'npm run build' 
}

def buildImage() {
  echo 'building the docker image...'
  withCredentials([usernamePassword(credentialsId: 'nexus-docker-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]){
    sh "docker build -t 192.168.1.77:8083/demo-app:${params.VERSION}"
    sh "echo $PASS | docker login -u $USER --password-stdin 192.168.1.77:8083"
    sh "docker push 192.168.1.77:8083/demo-app:${params.VERSION}"
  }
}

def testApp() {
  echo 'testing the app...'
}

def deployApp() {
  echo 'deploying the app...'
  echo "version ${params.VERSION}"
}

return this
