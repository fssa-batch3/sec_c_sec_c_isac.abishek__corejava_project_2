@echo off

set "current_dir=%cd%"

git status
git add .
git commit -m "commit"
git status
git pull origin main
git push orgin main

pause

