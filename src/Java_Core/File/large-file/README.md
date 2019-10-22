- An InputStream is a stream from which you can read bytes. 

    You wouldn't normally use this class directly, but if you did, you'd be expecting binary data. 
    It has many different subclasses for input from different sources, 
    such as FileInputStream, AudioInputStream, StringBufferInputStream and so on.

- An InputStreamReader is a wrapper for an InputStream, that converts the stream's bytes into characters, using any encoding you like.
 
    Normally, you specify the character encoding when you create the InputStreamReader. 
    There are a few different constructors that let you do this. 
    If you want to read text data, you could use an InputStreamReader. 
    Make your InputStream first to read the data, then wrap it in an InputStreamReader.

- A FileReader is a specialised InputStreamReader that can only read from files, and uses the default platform encoding.

    In other words, it assumes that the file that it reads has been created according to your operating system's settings for platform encoding. 
    This is usually OK; but if you're going to be reading files with different encodings, 
    you should create a FileInputStream and wrap it in an InputStreamReader.
    
If you are in Windows go to Control Panel -> Regional and Language Options Control Panel -> Advanced there you will see default encoding. 
FileReader always uses that encoding.

What makes FileReader different from FileInputStream is that 
FileReader is for reading text files in default encoding while FileInputStream is for reading binary files.