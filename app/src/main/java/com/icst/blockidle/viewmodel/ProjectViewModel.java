/*
 *  This file is part of Block IDLE.
 *
 *  Block IDLE is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Block IDLE is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *   along with Block IDLE.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.icst.blockidle.viewmodel;

import java.util.ArrayList;

import com.icst.blockidle.bean.ProjectBean;
import com.icst.blockidle.repository.ProjectRepository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProjectViewModel extends ViewModel {

	private final MutableLiveData<ArrayList<ProjectBean>> projects;
	private final ProjectRepository repository;

	public ProjectViewModel() {
		repository = ProjectRepository.getInstance();
		projects = repository.getMutableLiveProjects();
	}

	public LiveData<ArrayList<ProjectBean>> getProjects() {
		return projects;
	}

	public void refreshProjects() {
		projects.postValue(repository.getMutableLiveProjects().getValue());
	}
}
