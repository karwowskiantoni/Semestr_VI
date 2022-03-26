package classification.extraction.xml;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "REUTERS")
@XmlAccessorType(XmlAccessType.FIELD)
public class Reuters {
  @XmlElement(name = "PLACES")
  private Places PLACES;

  @XmlElement(name = "TEXT")
  private Text TEXT;

  public Text getTEXT() {
    return TEXT;
  }

  public void setTEXT(Text TEXT) {
    this.TEXT = TEXT;
  }

  public Places getPLACES() {
    return PLACES;
  }

  public void setPLACES(Places PLACES) {
    this.PLACES = PLACES;
  }
}
