/*package com.pidev.Pi.role;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pidev.Pi.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)

public class Role {
    @Id
    @GeneratedValue
    private Integer id;
    @Getter
    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;


    @CreatedDate
    @Column(nullable = false, updatable = false )
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false  )
    private LocalDateTime lastModifiedDate;


}*/
package com.pidev.Pi.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pidev.Pi.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ensures ID is auto-incremented
    private Integer id;

    @Column(unique = true, nullable = false) // Ensures role name is unique and required
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    // Explicitly define the getter method to avoid any issues
    public String getName() {
        return name;
    }
}
