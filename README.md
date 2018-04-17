### uta-android-app
Android app for a catering service
## Initialize the repsoitory
git clone https://github.com/wldarden/uta-android-app.git
## Modifying project to work on your computer
1. change the local.properties file to point to your sdk

## To develop a new feature
1. checkout and pull dev branch to get the most recent working version of code.
2. "git checkout -b \<newBranchName\>" will create a branch and check it out.
3. develop your code
4. Try to use commit messages. It will help later if theres problems I promise.
  this command will add all modified files, and take a commit message as an argument:
  git commit -am "added guide for git commits to the readme"
5. git push. you will probably need to do "git push --set-upstream origin \<newBranchName\>"
## To merge your feature into dev branch
1. make sure you pull any new additions to dev branch by other people since you branched off it. just checkut dev and pull, then checkout your feature branch again.
2. merge dev into your branch if there were any changes using this command while you have your feature branch checked out:
  git merge dev
3. If there are merge conflicts, fix them here. the output will tell you where the conflicts are. 
4. commit and push the merge conflicts if you had any. then go to the github page. 
5. Open a new pull request. choose dev on the left dropdown and your branch on the right dropdown. if there are no conflicts found, you'll be able to merge it in. try to use squash and merge, it will make the commit history a little cleaner, but it's not required.
## Restriction on commiting to the project
dev is the main branch, it will hold the most up to date version of the code. We won't be able to push directly to dev. The only way to get code into dev is through a pull request. This will force us to integrate our features with dev on the feature branch, keeping dev branch clean.

