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

package com.icst.blockidle.view;

import java.util.ArrayList;

import com.icst.blockidle.databinding.AdapterPaneBinding;
import com.icst.blockidle.databinding.NoPaneLayoutBinding;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class WorkspaceView extends LinearLayout {

	private final ArrayList<PaneView> panes;
	private PaneView currentPane;

	private LinearLayout paneHolderView;
	private RecyclerView panesList;
	private PaneListAdapter adapter;

	private LinearLayout noPaneLayout;

	private final int PANE_LAYOUT = 0;
	private final int NO_PANE_LAYOUT = 1;

	public WorkspaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		panes = new ArrayList<PaneView>();
		panesList = new RecyclerView(context);
		panesList.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		paneHolderView = new LinearLayout(context);
		paneHolderView.setOrientation(VERTICAL);

		adapter = new PaneListAdapter(panes);
		panesList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
		panesList.setAdapter(adapter);
		paneHolderView.addView(panesList);

		noPaneLayout = NoPaneLayoutBinding.inflate(LayoutInflater.from(context)).getRoot();

		noPaneLayout.setLayoutParams(new WorkspaceView.LayoutParams(WorkspaceView.LayoutParams.MATCH_PARENT,
				WorkspaceView.LayoutParams.MATCH_PARENT));
		paneHolderView.setLayoutParams(new WorkspaceView.LayoutParams(WorkspaceView.LayoutParams.MATCH_PARENT,
				WorkspaceView.LayoutParams.MATCH_PARENT));

		addView(noPaneLayout);
		addView(paneHolderView);

		showLayout(NO_PANE_LAYOUT);
	}

	private void showLayout(int layout) {
		noPaneLayout.setVisibility(layout == NO_PANE_LAYOUT ? VISIBLE : GONE);
		paneHolderView.setVisibility(layout == PANE_LAYOUT ? VISIBLE : GONE);
	}

	public void addPane(PaneView pane) {
		if (pane == null) {
			return;
		}
		currentPane = pane;
		panes.add(pane);
		adapter.notifyItemInserted(panes.size() - 1);
		pane.getView()
				.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		openPane(pane);
	}

	public void requestRelease() {
		if (currentPane == null) {
			return;
		}
		currentPane.onReleaseRequest();
	}

	public void openPane(PaneView pane) {
		if (paneHolderView.getChildAt(1) != null) {
			paneHolderView.removeViewAt(1);
		}
		paneHolderView.addView(pane.getView());
	}

	public class PaneListAdapter extends RecyclerView.Adapter<PaneListAdapter.ViewHolder> {

		private final ArrayList<PaneView> panes;

		public PaneListAdapter(ArrayList<PaneView> panes) {
			this.panes = panes;
		}

		@NonNull @Override
		public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			AdapterPaneBinding binding = AdapterPaneBinding.inflate(LayoutInflater.from(parent.getContext()));
			return new ViewHolder(binding);
		}

		@Override
		public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
			holder.binding.paneTitle.setText(panes.get(position).getTitle());
			holder.binding.paneIcon.setImageDrawable(panes.get(position).getIcon());
		}

		@Override
		public int getItemCount() {
			return panes.size();
		}

		public class ViewHolder extends RecyclerView.ViewHolder {
			public AdapterPaneBinding binding;

			public ViewHolder(AdapterPaneBinding binding) {
				super(binding.getRoot());
				this.binding = binding;
			}
		}
	}

}
