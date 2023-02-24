package com.sprint.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="transaction")
public class TransactionDTO {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="transaction_id")
		private long transactionId;

		@Column(name="start_date")
		private LocalDate date;

		@Column(name="end_date")
		private LocalDate date1;

		@Column(name="cost")
		private double cost;
		

}
