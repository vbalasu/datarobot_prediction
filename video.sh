# Make speed faster (resulting in smaller file)
ffmpeg -i 01_import_data.mov -filter:v "setpts=0.5*PTS" 01_import_data.gif
