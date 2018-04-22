### uta-android-app
Android app for a catering service
## Initialize the repsoitory
git clone https://github.com/wldarden/uta-android-app.git

## git commands
to pull the current version of the current branch from the origin:
git pull

to checkout an existing branch:

git checkout <branchName>

to create and checkout a new branch:

git checkout -b <branchName>

to see what branch you are on, and if you have uncommited modified files, and other info:

git status

to commit changes:

git commit -am "you need to have these quotes around this commit message or it wont work"

(above, the -a adds all the modified files to the commit for you)

to push changes to the back end:

git push

the first time you push you will have to do this:

git push --set-upsteam origin <yourBranchName>

to merge changes from one branch to another:

git merge <otherBranchName>

this will merge <otherBranchName> into the branch you are on right now. to see what branch you are on, use git status.

## common problems in the above flow:
### the origin contains commits that you do not have locally
If you try to push, but get an error comes up saying there are changes you dont have yet. This means
someone pushed to this branch in between when you last pulled and now. just git pull again. it will
likely auto merge and you can imediately git push after that. If Vim pops up with hashtags all down the screen,
just hit escape once, and then type :wq and hit enter. the : means you are giving it a command, the w
means write, and q stands for quit. basically you are just giving a blank commit message to the merge
you just did. after that, git push.

### merge conflicts:

are annoying. this is what they look like:

"<<<<<<<<<<<Head

[this part contains the version of the code that you just wrote on your new branch]

==========

[this part contains the version of code that exists in the origin on the github servers]

">>>>>>>>>>>BranchName"

most of the time you want whats in the head. which means you would delete the bottom half of that
segment. Look at each conflict on a case by case basis though. Regression errors are COMMON in merge
conflicts, so take a second to see which segment of code is the one you want in the final version you
 are about to push.
 most of the time its either all the top and none of the bottom or vice versa, but sometimes youll want
 a few lines from each.

 after you resolve merge conflicts, you will have to commit and push again.

## You try to checkout another branch but there are unsaved changes on your current branch
do a git status to see what files are modified. If those are not changes you want to keep,
just do git stash to reset the branch back to the last commit. now you can git checkout.

if you wanted to apply those changes to a different branch, do git stash, and then checkout the branch
you want those changes on. then do git stash pop. git will try to automatically apply the changes.

in rare cases, you might have to do git reset --hard origin. this will forget any local changes permanently
and set the branch back to what the origin has.