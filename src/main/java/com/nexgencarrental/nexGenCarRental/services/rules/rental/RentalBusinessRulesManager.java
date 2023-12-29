package com.nexgencarrental.nexGenCarRental.services.rules.rental;

import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.AddRentalRequest;
import com.nexgencarrental.nexGenCarRental.services.dtos.requests.rental.UpdateRentalRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class RentalBusinessRulesManager implements RentalBusinessRulesService{
    public void validateAddRentalRequest(AddRentalRequest addRentalRequest) {
        // Başlangıç tarihi kontrolü
        if (addRentalRequest.getStartDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Başlangıç tarihi " + LocalDate.now() + " , yani bugünden daha önce bir tarih olamaz");
        }

        // Bitiş tarihi kontrolü
        if (addRentalRequest.getEndDate().isBefore(addRentalRequest.getStartDate())) {
            throw new RuntimeException("Girdiğiniz tarih, " + addRentalRequest.getStartDate() + " bu tarihden ileri bir tarih olmalıdır.");
        }

        // Kiralama süresi kontrolü
        if (ChronoUnit.DAYS.between(addRentalRequest.getStartDate(), addRentalRequest.getEndDate()) > 25
                || 0 == ChronoUnit.DAYS.between(addRentalRequest.getStartDate(), addRentalRequest.getEndDate())) {
            throw new RuntimeException("Bir araç minimum 1 gün, maksimum 25 gün kiralanabilir.");
        }
    }
    public void validateUpdateRentalRequest(UpdateRentalRequest updateRentalRequest) {
        // Başlangıç tarihi kontrolü
        if(updateRentalRequest.getStartDate().isBefore(LocalDate.now())){
            throw new RuntimeException("Başlangıç tarihi " + LocalDate.now() + " , yani bugünden daha önce bir tarih olamaz");
        }

        // Bitiş tarihi kontrolü
        if (updateRentalRequest.getEndDate().isBefore(updateRentalRequest.getStartDate())){
            throw new RuntimeException("Girdiğiniz tarih, " + updateRentalRequest.getStartDate() + " bu tarihden ileri bir tarih olmalıdır.");
        }

        // Kiralama süresi kontrolü
        if (ChronoUnit.DAYS.between(updateRentalRequest.getStartDate(), updateRentalRequest.getEndDate())  > 25
                || 0 == ChronoUnit.DAYS.between(updateRentalRequest.getStartDate(), updateRentalRequest.getEndDate())) {
            throw new RuntimeException("Bir araç minimum 1 gün, maksimum 25 gün kiralanabilir.");
        }
    }
}
