echo 'starting 3 processes of asset management';

# starting author service
echo 'starting author service';
. run/run-author-service.sh > /dev/null
AUTHOR_PID=$!

if [ -z $AUTHOR_PID ]
then
  wait
fi

echo 'author service started on port 8081.';

# starting search service
echo 'starting search service';
. run/run-search-service.sh > /dev/null 
SEARCH_PID=$!

if [ -z $SEARCH_PID ]
then
  wait
fi

echo 'search service started on port 8082.';

# starting user service
echo 'starting user service';
. run/run-user-service.sh > /dev/null
USER_PID=$!

if [ -z $USER_PID ]
then
  wait
fi


echo 'user service started on port 8083.';
echo '########################################'
echo 'PID of Author Service:'$AUTHOR_PID
echo 'PID of Search Service:'$SEARCH_PID
echo 'PID of User Service:'$USER_PID
echo '########################################'
echo 'Save the PIDs for killing those later.'
