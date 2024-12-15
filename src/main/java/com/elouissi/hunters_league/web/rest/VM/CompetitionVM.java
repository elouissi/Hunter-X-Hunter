        package com.elouissi.hunters_league.web.rest.VM;

        import com.elouissi.hunters_league.domain.enums.SpeciesType;
        import com.elouissi.hunters_league.validation.MinDaysInFuture;
        import com.elouissi.hunters_league.validation.UniqueCode;
        import jakarta.validation.constraints.*;
        import lombok.*;
        import java.time.LocalDateTime;

        @Getter
        @Setter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public class CompetitionVM {


            @UniqueCode(message = "le code du competition déja existe")
            @NotBlank(message = "Code is required")
            @Pattern(regexp = "^[a-zA-Z]+_\\d{4}-\\d{2}-\\d{2}$", message = "Le code doit suivre le format 'lieu_YYYY-MM-DD'")
            private String code;

            @NotBlank(message = "Location is required")
            private String location;

            @NotNull(message = "Date is required")
            @Future(message = "Date must be in the future")
            @MinDaysInFuture(days = 2, message = "The date must be at least 2 days in the future")
            private LocalDateTime date;

            @NotNull(message = "SpeciesType is required")
            private SpeciesType speciesType;

            @NotNull(message = "Minimum participants is required")
            @Min(value = 0, message = "Minimum participants must be at least 0")
            private Integer minParticipants;

            @NotNull(message = "Maximum participants is required")
            @Min(value = 1, message = "Maximum participants must be at least 1")
            private Integer maxParticipants;

            @NotNull(message = "Open registration status is required")
            private Boolean openRegistration;
        }
