if [ $# -eq 0 ]
then
  echo "Syntax: ./video.sh <filename without extension>"
  echo "Example: ./video.sh 01_first_video"
  exit 1
fi
# Make speed faster (resulting in smaller file)
ffmpeg -i $1.mov -filter:v "setpts=0.5*PTS" $1.gif
