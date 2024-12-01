import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.example.testinterview.R
import com.example.testinterview.base.BaseBottomSheetDialogFragment
import com.example.testinterview.databinding.FragmentSortBottomSheetDialogBinding
import com.example.testinterview.views.component.adapter.SortOptionAdapter
import com.example.testinterview.views.component.model.SortOption
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class SortBottomSheetDialogFragment : BaseBottomSheetDialogFragment() {

    private lateinit var binding: FragmentSortBottomSheetDialogBinding
//    private lateinit var adapter: SortOptionAdapter


    override fun getLayoutResId(): Int = R.layout.fragment_sort_bottom_sheet_dialog

    override fun setupViews(view: View) {
        binding = FragmentSortBottomSheetDialogBinding.bind(view)
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()

        try {
            val dialog = dialog as BottomSheetDialog
            val bottomSheet =
                dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as ViewGroup?
            if (bottomSheet != null) {
                bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT

                val behavior = BottomSheetBehavior.from(bottomSheet)
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                Log.e("SortBottomSheetDialog", "BottomSheet view is null")
            }
        } catch (e: Exception) {
            Log.e("SortBottomSheetDialog", "Error in onStart: ${e.message}")
        }
    }

    private fun setupRecyclerView() {
//        adapter = SortOptionAdapter()
//        binding.recyclerView.adapter = adapter
//        adapter.submitList(sortOptions)
//        adapter.onItemClick = { sortOption ->
            // Handle sort option selected
//            println("Selected sort option: ${sortOption.name}")
            dismiss()
//        }
    }
}
