var sendOffer = "";
var items = [];
var status = false;


function shareCloset() {
	//needs to click the preshare button, then click the share button for each items
	//create a loop that will keep running true as long as there is no captcha
	// get responces for successfull shares, share that to a log that can  be displayed
	//is there a way to send the user a text or email when its captchad???

	sendOffers = chrome.storage.local.get('sendOffers', function (items) {
	sendOffers = items.sendOffers;

      var closet = [];
			closet = $(".tile");
      //alert(sendOffers);  IT WILL DISPLAY THE RIGHT NUMBER HERE!
      alert(closet[0].id);
      var url = closet[0].firstChild.pathname;
      alert(url);
      window.location.href  = url;

    });
    alert(sendOffers);
    }
      // $(closet).each(function (index) {
			// 	var el = $(this);
      //   setTimeout(function(){
      //   el.find(".href").click();




      //
      // }, 4000);



//           console.log(index);
//           console.log(el[0].childNodes[1].childNodes["0"].textContent);
//       });
//     }
//
//
//
//     else
// 			var items = $(".tile");
// 			$(items).each(function (index, value)) {
// 				var el = $(this);
// 				setTimeout(function () {
// 					el.find(".href")[0].click();
//
//
// 				});
//
// 			}
// 			else {
// 				alert("not loaded yet");
// 				console.log("not loaded yet");
//       }
// 			}
// 		});
// sendOffers = 2; chrome.storage.local.set({
// 		'sendOffers': sendOffers
// 	});
//   discountPercent = 10; chrome.storage.local.set({
// 		'discountPercent': discountPercent
// 	});
//
// 	// var shareStatus = "";
// 	// chrome.storage.local.get('shareStatus', function (result) {
// 	//    var shareStatus = result.shareStatus;
// 	//
// 	//   if (shareSatus == 1) {
// 	//     getUserCloset();
// 	//   }
// 	//
// 	// });
// 	//
//
//
// // }
//
// function offerToLikers() {
// 	//need to go to each item page ie. https://poshmark.com/listing/Simulated-Morganite-Three-Stone-ring-5ac95bcc72ea887f6bf6b3aa
// 	//click the "Price Drop" link and a new page will come up
// 	//click the offer to likers
// 	//if couldnt get price before, price is in this page
// 	//in the offer text take the price of the item and calc the price discount.
// 	//select the first tab -$1.50
//
// }
//
 shareCloset();
