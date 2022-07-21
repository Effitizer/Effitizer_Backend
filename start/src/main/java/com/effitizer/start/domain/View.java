package com.effitizer.start.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class View{
    @Id
    @GeneratedValue
    @Column(name = "view_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contents_id")
    public Contents contents; //책 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user; //책 id

    @CreatedDate
    private LocalDateTime createdDate;

    @Builder
    public View (Long id, Contents contents, User user) {
        this.id = id;
        this.setContents(contents);
        this.setUser(user);
    }

    public void setContents(Contents contents) {
        this.contents = contents;
        contents.getViews().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getViews().add(this);
    }
}
