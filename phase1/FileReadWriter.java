class FileReadWriter {

    public EventManager readFromEventFile(String path) throws ClassNotFoundException {
        try {
            InputStream file = new FileInputStream(path); // String path should be "fileName.ser"
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            // deserialize the EventManager
            EventManager sm = (EventManager) input.readObject();
            input.close();
            return sm;
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Cannot read from input file, returning" +
                    "a new EventManager.", ex);
            return new EventManager();
        }
    }

    public SpeakerManager readFromSpeakerFile(String path) throws ClassNotFoundException {
        try {
            InputStream file = new FileInputStream(path); // String path should be "fileName.ser"
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            // deserialize the SpeakerManager
            SpeakerManager sm = (SpeakerManager) input.readObject();
            input.close();
            return sm;
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Cannot read from input file, returning" +
                    "a new SpeakerManager.", ex);
            return new SpeakerManager();
        }
    }

    public RoomManager readFromRoomFile(String path) throws ClassNotFoundException {
        try {
            InputStream file = new FileInputStream(path); // String path should be "fileName.ser"
            InputStream buffer = new BufferedInputStream(file);
            ObjectInput input = new ObjectInputStream(buffer);

            // deserialize the RoomManager
            RoomManager sm = (RoomManager) input.readObject();
            input.close();
            return sm;
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Cannot read from input file, returning" +
                    "a new RoomManager.", ex);
            return new RoomManager();
        }
    }
}