package model.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "PLACES")
@XmlAccessorType(XmlAccessType.FIELD)
public class Places {
  @XmlElement(name = "D")
  private List<String> D;

  public List<String> getD() {
    return D;
  }

  public void setD(List<String> d) {
    D = d;
  }
}
