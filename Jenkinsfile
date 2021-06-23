pipeline {
  agent {
    kubernetes {
      yaml """
spec:
  containers:
  - name: helm
    image: alpine/helm:3.5.4
    command:
    - cat
    tty: true
  - name: buildkit
    image: moby/buildkit:v0.8.3-rootless
    command:
    - cat
    tty: true
"""
    }
  }
  stages {
    stage('Build Container Image') {
      steps {
        container('buildkit'){
          retry(3) {
            sh "buildctl --addr tcp://buildkitd:1234 build --frontend=dockerfile.v0 --local context=`pwd` --local dockerfile=`pwd` --output type=image,name=registry.container-registry:5000/chestnut/frontend,push=true,registry.insecure=true"
          }
        }
      }
    }

    stage('Deploy For Test') {
      steps {
        container('helm'){
          sh "set +e; helm delete frontend-test ; exit 0"
          sh "helm upgrade --install frontend-test --wait --cleanup-on-fail ./frontend-chart"
        }
      }
    }
  
    stage('Test Test'){
      failFast true
      parallel {
        stage('Get /'){
          steps{
            httpRequest responseHandle: 'NONE', url: 'http://frontend-test-frontend-chart/', wrapAsMultipart: false
          }
        }
        stage('Get /index.html'){
          steps{
            httpRequest responseHandle: 'NONE', url: 'http://frontend-test-frontend-chart/index.html', wrapAsMultipart: false
          }
        }
      }
    }
  
    stage('Clean up Test') {
      steps {
        container('helm') {
          sh "helm delete frontend-test ; exit 0"
        }
      }
    }


    stage('Deploy For Production') {
      steps {
        container('helm'){
          sh "helm upgrade --install frontend --wait --cleanup-on-fail --recreate-pods ./frontend-chart"
        }
      }
    }
    
  }
}