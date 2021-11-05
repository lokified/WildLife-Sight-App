public interface DatabaseManagement {
    public boolean equals(Object otherObject);
    public void save();
    public void update(int id, String newName, int newSightingId);
    public void delete();
}
