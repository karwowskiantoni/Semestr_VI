package classification.model.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "REUTERSLIST")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReutersList {

  @XmlElement(name = "REUTERS")
  private List<Reuters> reutersList;

  public List<Reuters> getReutersList() {
    return reutersList;
  }

  public void setReutersList(List<Reuters> reutersList) {
    this.reutersList = reutersList;
  }
}
