package mapstruct.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto {

  private String customerName;
  private String shippingStreetAddress;
  private String shippingCity;
  private String billingStreetAddress;
  private String billingCity;
}
