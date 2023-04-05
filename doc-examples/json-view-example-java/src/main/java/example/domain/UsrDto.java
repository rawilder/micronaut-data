package example.domain;

import example.domain.view.UsrView;
import io.micronaut.serde.annotation.Serdeable;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Serdeable
public class UsrDto {
    private Long id;
    private String name;
    private Period ym;
    private Duration ds;
    private Double bd;

   // private OffsetDateTime tstz;

    private LocalDateTime ts;

    private LocalDate date;

    public UsrDto(Long id, String name, Period ym, Duration ds, Double bd) {
        this.id = id;
        this.name = name;
        this.ym = ym;
        this.ds = ds;
        this.bd = bd;
    }

    public UsrDto(Long id, String name, Period ym, Duration ds, Double bd,
                  /*OffsetDateTime tstz,*/ LocalDateTime ts, LocalDate date) {
        this.id = id;
        this.name = name;
        this.ym = ym;
        this.ds = ds;
        this.bd = bd;
        //this.tstz = tstz;
        this.ts = ts;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Period getYm() {
        return ym;
    }

    public void setYm(Period ym) {
        this.ym = ym;
    }

    public Duration getDs() {
        return ds;
    }

    public void setDs(Duration ds) {
        this.ds = ds;
    }

    public Double getBd() {
        return bd;
    }

    public void setBd(Double bd) {
        this.bd = bd;
    }

   /* public OffsetDateTime getTstz() {
        return tstz;
    }

    public void setTstz(OffsetDateTime tstz) {
        this.tstz = tstz;
    } */

    public LocalDateTime getTs() {
        return ts;
    }

    public void setTs(LocalDateTime ts) {
        this.ts = ts;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public static UsrDto fromUsr(Usr usr) {
        if (usr == null) {
            return null;
        }
        return new UsrDto(usr.getId(), usr.getName(), usr.getYm(), usr.getDs(), usr.getBd());
    }

    public static UsrDto fromUsrView(UsrView usrView) {
        if (usrView == null) {
            return null;
        }
        return new UsrDto(usrView.getUsrId(), usrView.getName(), usrView.getYm(), usrView.getDs(), usrView.getBd(),
            /*usrView.getTstz(),*/ usrView.getTs(), usrView.getDate());
    }
}
