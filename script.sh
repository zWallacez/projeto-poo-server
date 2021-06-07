#!/bin/sh

# Credits: http://stackoverflow.com/a/750191

git filter-branch -f --env-filter "
    GIT_AUTHOR_NAME='ianito'
    GIT_AUTHOR_EMAIL='iaan.developer@gmail.com'
    GIT_COMMITTER_NAME='ianito'
    GIT_COMMITTER_EMAIL='iaan.developer@gmail.com'
  " HEAD
