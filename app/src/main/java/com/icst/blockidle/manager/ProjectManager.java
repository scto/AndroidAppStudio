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

package com.icst.blockidle.manager;

import java.util.ArrayList;

import com.icst.blockidle.bean.ProjectBean;

public final class ProjectManager {

	// Currently Dummy Projects
	public static ArrayList<ProjectBean> getProjects() {
		ArrayList<ProjectBean> projects = new ArrayList<>();

		ProjectBean project1 = new ProjectBean();
		project1.setProjectName("Block Editor");
		project1.setProjectVersionName("1.0.0");
		project1.setProjectVersion("100");

		ProjectBean project2 = new ProjectBean();
		project2.setProjectName("XML Parser");
		project2.setProjectVersionName("2.1.0");
		project2.setProjectVersion("210");

		ProjectBean project3 = new ProjectBean();
		project3.setProjectName("Gradle IDE");
		project3.setProjectVersionName("0.9.5");
		project3.setProjectVersion("95");

		ProjectBean project4 = new ProjectBean();
		project4.setProjectName("Java Compiler");
		project4.setProjectVersionName("3.0.2");
		project4.setProjectVersion("302");

		ProjectBean project5 = new ProjectBean();
		project5.setProjectName("Kotlin Playground");
		project5.setProjectVersionName("1.5.1");
		project5.setProjectVersion("151");

		ProjectBean project6 = new ProjectBean();
		project6.setProjectName("Android Build Tool");
		project6.setProjectVersionName("4.2.0");
		project6.setProjectVersion("420");

		ProjectBean project7 = new ProjectBean();
		project7.setProjectName("Terminal Emulator");
		project7.setProjectVersionName("2.8.3");
		project7.setProjectVersion("283");

		ProjectBean project8 = new ProjectBean();
		project8.setProjectName("Script Runner");
		project8.setProjectVersionName("1.7.0");
		project8.setProjectVersion("170");

		ProjectBean project9 = new ProjectBean();
		project9.setProjectName("Python Executor");
		project9.setProjectVersionName("3.9.1");
		project9.setProjectVersion("391");

		ProjectBean project10 = new ProjectBean();
		project10.setProjectName("Java Debugger");
		project10.setProjectVersionName("2.4.0");
		project10.setProjectVersion("240");

		ProjectBean project11 = new ProjectBean();
		project11.setProjectName("Project Manager");
		project11.setProjectVersionName("1.0.5");
		project11.setProjectVersion("105");

		ProjectBean project12 = new ProjectBean();
		project12.setProjectName("UI Layout Designer");
		project12.setProjectVersionName("3.2.1");
		project12.setProjectVersion("321");

		projects.add(project1);
		projects.add(project2);
		projects.add(project3);
		projects.add(project4);
		projects.add(project5);
		projects.add(project6);
		projects.add(project7);
		projects.add(project8);
		projects.add(project9);
		projects.add(project10);
		projects.add(project11);
		projects.add(project12);

		return projects;
	}
}
