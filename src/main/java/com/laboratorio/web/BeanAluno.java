package com.laboratorio.web;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.laboratorio.model.Aluno;
import com.laboratorio.util.*;

@ManagedBean(name = "bAluno")
@RequestScoped
public class BeanAluno implements Serializable {

	private static final long serialVersionUID = 1L;

	public List<Aluno> getListagem() {
		Connection conexao = null;
		PreparedStatement psSelect = null;
		ResultSet rsSelect = null;
		String sql = "SELECT matricula, nome, idade, sexo, peso, altura FROM aluno";

		List<Aluno> lsAlunos = null;

		try {
			conexao = ConnectionFactory.getConnection();
			psSelect = conexao.prepareStatement(sql);
			rsSelect = psSelect.executeQuery();
			lsAlunos = new ArrayList<Aluno>();
			while (rsSelect.next()) {
				Aluno aluno = new Aluno();
				aluno.setMatricula(rsSelect.getInt("matricula"));
				aluno.setNome(rsSelect.getString("nome"));
				aluno.setIdade(rsSelect.getInt("idade"));
				aluno.setSexo(rsSelect.getString("sexo"));
				aluno.setPeso(rsSelect.getFloat("peso"));
				aluno.setAltura(rsSelect.getFloat("altura"));
				lsAlunos.add(aluno);
			}
		} catch (Exception e) {
			System.out.println("--------------------");
			System.out.println("Erro no select");
			e.printStackTrace();
		}

		return lsAlunos;
	}

}
