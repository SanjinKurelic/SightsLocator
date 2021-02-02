package eu.sanjin.contracts;

public interface ViewHolderContract {

  void setTitle(String title);
  void setImage(String imagePath);
  default void setDescription(String description) {}

}
